package com.example.quartsdemo.batch.listener;

import com.example.quartsdemo.batch.repository.entity.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// Job의 실행 결과를 확인하여 BatchStatus가 COMPLETED인지 검사합니다.
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			// BatchStatus가 COMPLETED인 경우, 로깅을 통해 작업 완료를 알립니다.
			LOGGER.info("!!! JOB FINISHED! Time to verify the results");

			// 데이터베이스에서 쿼리를 수행할 SQL 문을 정의합니다. 이 예제에서는 coffee 테이블에서 필요한 컬럼을 선택하도록 SQL을 작성했습니다.
			String query = "SELECT brand, origin, characteristics, processed, coffee_id FROM coffee";

			// jdbcTemplate를 사용하여 데이터베이스 쿼리를 실행하고 결과를 받아옵니다.
			// 결과를 Coffee 객체로 매핑하여 리스트로 저장하고, forEach를 사용하여 결과를 로깅합니다.
			jdbcTemplate
					.query(query,
							// rs: 결과셋, row: 결과의 인덱스
							(rs, row) -> new Coffee(
									rs.getLong(5),
									rs.getString(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4)
							)
					)
					.forEach(coffee -> LOGGER.info("Found < {} > in the database.", coffee));
		}
	}
}
