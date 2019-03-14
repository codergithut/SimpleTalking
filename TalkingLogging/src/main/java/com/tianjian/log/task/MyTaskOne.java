package com.tianjian.log.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @ProjectName: com.tianjian.log.task
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/13
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/13
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */

public class MyTaskOne implements Tasklet {

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("MyTaskOne start..");

        // ... your code

        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }
}
