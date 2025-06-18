package com.algorithmsolutionproject.algorithmsolution.service.room;

import com.algorithmsolutionproject.algorithmsolution.dto.room.GetSolvedProblemResultResponse;
import com.algorithmsolutionproject.algorithmsolution.entity.Submission;
import com.algorithmsolutionproject.algorithmsolution.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomResultService {
    private final SubmissionRepository submissionRepository;

    // 문제 풀이 결과 조회
    @Transactional
    public GetSolvedProblemResultResponse getSolveProblemResult(Integer roomId) {
        List<Submission> result = submissionRepository.findLatestSubmissionsPerUserInRoom(roomId);
        return GetSolvedProblemResultResponse.from(result);
    }
}
