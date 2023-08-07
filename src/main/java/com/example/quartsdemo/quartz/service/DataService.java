package com.example.quartsdemo.quartz.service;

import com.example.quartsdemo.quartz.entity.JobEntity;
import com.example.quartsdemo.quartz.entity.ResultBody;

public interface DataService {
    ResultBody getAll();

    void save(JobEntity job);

    ResultBody getAllByPage(int page, int limit);

    String updStatus(boolean start, int id);

    JobEntity getUpdData(int id);

    void delJob(int id);

    void updData(JobEntity job);
}
