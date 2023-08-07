package com.example.quartsdemo.quartz.service;

import com.example.quartsdemo.quartz.entity.JobEntity;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;

public interface QuartzService {
    JobEntity getById(int id);

    JobEntity getJobEntityById(Integer id);

    JobKey getJobKey(JobEntity entity);

    JobDataMap getJobDataMap(JobEntity entity);

    JobDetail geJobDetail(JobKey jobKey, String description, JobDataMap map);

    Trigger getTrigger(JobEntity entity);

    List<JobEntity> loadJobs();
}
