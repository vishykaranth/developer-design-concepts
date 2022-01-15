package com.javabydeveloper.template;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.javabydeveloper.model.User;
import com.javabydeveloper.template.callback.UserBatchPreparedStatementSetter;
import com.javabydeveloper.util.BatchQueryBuilder;

@Service
public class UserBatchUpdateService {

	private static final Logger log = LoggerFactory.getLogger(UserBatchUpdateService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Value("${jdbc.batch_insert_size}")
	private int batchInsertSize;
	
	@Value("${jdbc.batch_update_size}")
	private int batchUpdateSize;
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(55);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchInsert(List<User> users) {
		StopWatch timer = new StopWatch();

		String sql = "INSERT INTO `USER` (USERNAME, PASSWORD, CREATEDTIME, UPDATEDTIME, USERTYPE, DATEOFBIRTH)"
				+ " VALUES(?,?,?,?,?,?)";

		timer.start();
		jdbcTemplate.batchUpdate(sql, new UserBatchPreparedStatementSetter(users));

		timer.stop();
		log.info("batchInsert -> Total time in seconds: " + timer.getTotalTimeSeconds());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchUpdate(List<User> users) {
		StopWatch timer = new StopWatch();
		String sql = "UPDATE `USER` SET USERNAME=:name, UPDATEDTIME=:updatedAt WHERE ID=:id";
	
		List<MapSqlParameterSource> params = new ArrayList<MapSqlParameterSource>();

		for (User u : users) {
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue("name", u.getUserName().toUpperCase());
			source.addValue("updatedAt", new Date());
			source.addValue("id", u.getId());

			params.add(source);
		}

		timer.start(); 
		namedJdbcTemplate.batchUpdate(sql, params.toArray(MapSqlParameterSource[]::new));

		timer.stop();
		log.info("batchUpdate -> Total time in seconds: " + timer.getTotalTimeSeconds());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchInsertAsync(List<User> users) throws InterruptedException, ExecutionException {
		StopWatch timer = new StopWatch();

		String sql = "INSERT INTO `USER` (USERNAME, PASSWORD, CREATEDTIME, UPDATEDTIME, USERTYPE, DATEOFBIRTH)"
				+ " VALUES(?,?,?,?,?,?)";

		final AtomicInteger sublists = new AtomicInteger();

		CompletableFuture[] futures = users.stream()
				.collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / batchInsertSize))
				.values()
				.stream()
				.map(ul -> runBatchInsert(ul, sql))
				.toArray(CompletableFuture[]::new);

		CompletableFuture<Void> run = CompletableFuture.allOf(futures);

		timer.start();
		run.get();
		timer.stop();

		log.info("batchInsertAsync -> Total time in seconds: " + timer.getTotalTimeSeconds());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchUpdateStatic(List<User> users) throws InterruptedException, ExecutionException {
		StopWatch timer = new StopWatch();

		final AtomicInteger sublists = new AtomicInteger();

		String[] queries = users.stream()
				.collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / batchUpdateSize))
				.values()
				.stream()
				.map(ul -> BatchQueryBuilder.buildUpdateQuery(ul))
				.toArray(String[]::new);

		timer.start();
		//System.out.println(Arrays.asList(queries));
		jdbcTemplate.batchUpdate(queries);
		timer.stop();

		log.info("batchUpdateStatic -> Total time in seconds: " + timer.getTotalTimeSeconds());
	}

	public CompletableFuture<Void> runBatchInsert(List<User> users, String sql) {
		return CompletableFuture.runAsync(() -> {
			jdbcTemplate.batchUpdate(sql, new UserBatchPreparedStatementSetter(users));
		}, executor);
	}
	
}
