package com.example.quartz.controller;

import com.example.quartz.config.response.Response;
import com.example.quartz.service.JobManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * job操作Controller
 *
 * @author wizhi
 * @date 2019/4/9
 */
@RestController
@RequestMapping(value = "/quartz/job")
public class JobController {

    @Autowired
    private JobManageService jobManageService;

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public Response pauseJob(@RequestParam(name = "jobName") String jobName,
                             @RequestParam(name = "jobGroup") String jobGroup) {

        jobManageService.pauseJob(jobName, jobGroup);
        return Response.success();
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public Response resumeJob(@RequestParam(name = "jobName") String jobName,
                              @RequestParam(name = "jobGroup") String jobGroup) {

        jobManageService.resumeJob(jobName, jobGroup);
        return Response.success();
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response deleteJob(@RequestParam(name = "jobName") String jobName,
                              @RequestParam(name = "jobGroup") String jobGroup) {

        jobManageService.deleteJob(jobName, jobGroup);
        return Response.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateJob(@RequestParam(name = "jobName") String jobName,
                              @RequestParam(name = "jobGroup") String jobGroup,
                              @RequestParam(name = "cronExpression") String cronExpression) {

        jobManageService.updateCronExpression(jobName, jobGroup, cronExpression);
        return Response.success();
    }

}
