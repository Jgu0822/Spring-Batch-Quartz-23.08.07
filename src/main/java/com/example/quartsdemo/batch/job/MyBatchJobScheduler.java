package com.example.quartsdemo.batch.job;

import com.example.quartsdemo.quartz.job.DynamicJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBatchJobScheduler extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(MyBatchJobScheduler.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            // 배치 작업 실행에 필요한 파라미터를 설정합니다. (여기서는 빈 파라미터로 설정)
            JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

            // DynamicJob 클래스의 execute 메서드를 직접 호출하여 배치 작업 실행
            DynamicJob dynamicJob = new DynamicJob();
            dynamicJob.execute(context);

            // 배치 작업이 실행되었다는 로그를 출력하거나 필요한 로직을 추가할 수 있습니다.
            System.out.println("배치 작업이 성공적으로 실행되었습니다!");

        } catch (Exception e) {
            // 배치 작업 실행 중 예외가 발생한 경우 처리
            e.printStackTrace();
            throw new JobExecutionException(e.getMessage());
        }
    }
}
