package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.room.execution.GetExecutionListInRoomResonse;
import com.algorithmsolutionproject.algorithmsolution.dto.room.submission.GetSubmissionsInRoomResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import com.algorithmsolutionproject.algorithmsolution.repository.ExecutionRepository;
import com.algorithmsolutionproject.algorithmsolution.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomCodeService {
    private final ExecutionRepository executionRepository;
    private final SubmissionRepository submissionRepository;

    // 특정 문제에 대한 내 실행 내역 조회
    @Transactional
    public GetExecutionListInRoomResonse getExecutionListInRoom(Integer userId, Integer roomId, Integer problemId) {
        List<Execution> executions = executionRepository.findByRoomIdAndUserIdAndProblemId(roomId, userId,
                problemId);
        return GetExecutionListInRoomResonse.from(executions);
    }

    // 특정 문제에 대한 내 제출 내역 조회
    @Transactional
    public GetSubmissionsInRoomResponse getSubmissionsInRoom(Integer userId, Integer roomId, Integer problemId) {
        List<Submission> submissions = submissionRepository.findByRoomIdAndUserIdAndProblemId(roomId, userId,
                problemId);
        return GetSubmissionsInRoomResponse.from(submissions);
    }

}
