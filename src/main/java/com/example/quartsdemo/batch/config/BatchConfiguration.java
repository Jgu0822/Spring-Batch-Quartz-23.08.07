package com.example.quartsdemo.batch.config;

import com.example.quartsdemo.batch.listener.JobCompletionNotificationListener;
import com.example.quartsdemo.batch.processor.CoffeeItemProcessor;
import com.example.quartsdemo.batch.repository.CoffeeRepository;
import com.example.quartsdemo.batch.repository.entity.Coffee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BatchConfiguration {

	@Autowired
	private CoffeeRepository coffeeRepository;

	// "importUserJob"이라는 이름의 Spring Batch Job을 생성합니다.
	// Job을 생성할 때는 JobBuilder를 사용합니다.
	@Bean
	public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1) {
		return new JobBuilder("importUserJob", jobRepository)
				.listener(listener)  					// Job 수행 시 이벤트를 처리할 JobCompletionNotificationListener를 지정합니다.
				.incrementer(new RunIdIncrementer())	// Job의 실행 ID를 증가시키는 RunIdIncrementer를 지정합니다.
				.flow(step1)							// Job의 실행 흐름을 지정합니다. step1을 실행하고, 그 다음 단계를 실행합니다.
				.end()									// Job의 실행이 끝났음을 나타냅니다.
				.build(); 								// Job을 빌드하여 반환합니다.
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository)
				.<Coffee, Coffee>chunk(1, transactionManager) 	// 1개씩 청크 단위로 처리하며, 트랜잭션 관리는 transactionManager로 지정합니다.
				.reader(repositoryReader()) 							// ItemReader로 repositoryReader()를 사용합니다.
				.processor(processor()) 								// ItemProcessor로 processor()를 사용합니다.
				.writer(repositoryWriter())								// ItemWriter로 repositoryWriter()를 사용합니다.
				.build(); 												// Step을 빌드하여 반환합니다.
	}

	@Bean
	public RepositoryItemReader repositoryReader() {

		Map<String, Direction> sortMap = new HashMap<>();
		// 데이터를 읽어올 때, brand 컬럼을 기준으로 오름차순으로 정렬합니다.
		sortMap.put("brand", Direction.ASC);

		// Spring Batch의 RepositoryItemReader를 생성합니다.
		// Coffee 타입을 읽어올 것을 명시합니다.
		return new RepositoryItemReaderBuilder<Coffee>()
				.name("RepositoryItemReader") 			// ItemReader의 이름을 지정
				.repository(this.coffeeRepository) 		// 읽어올 데이터를 제공하는 CoffeeRepository를 지정
				.pageSize(1) 							// 페이지 크기를 1로 설정하여 1개씩 데이터를 읽어옵니다.
				.sorts(sortMap) 						// 데이터를 정렬할 기준을 sortMap으로 지정
				.methodName("findAll") 					// 데이터를 읽어올 메서드를 findAll로 지정합니다.
				.build(); 								// RepositoryItemReader를 빌드하여 반환합니다.
	}

	@Bean
	public RepositoryItemWriter repositoryWriter() {
		return new RepositoryItemWriterBuilder<Coffee>()
				.repository(this.coffeeRepository) 		// 데이터를 쓸 CoffeeRepository를 지정
				.methodName("save") 					// 데이터를 쓸 메서드를 save로 지정
				.build(); 								// RepositoryItemWriter를 빌드하여 반환
	}

	@Bean
	public CoffeeItemProcessor processor() {

		return new CoffeeItemProcessor();
	}

}
