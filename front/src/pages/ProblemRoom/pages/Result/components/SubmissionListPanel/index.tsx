/* eslint-disable @typescript-eslint/no-explicit-any */
import ProblemHeader from '@pages/ProblemRoom/pages/components/ProblemHeader';
import SubmissionCard from '../SubmissionCard';

interface SubmissionListPanelProps {
  submissions: any[];
  selectedIdx: number;
  onSelect: (idx: number) => void;
}

export default function SubmissionListPanel({
  submissions,
  selectedIdx,
  onSelect,
}: SubmissionListPanelProps) {
  return (
    <div className="h-screen w-full max-w-xl overflow-y-auto bg-white">
      <ProblemHeader />
      <div className="p-6">
        <input
          type="text"
          value="1923. 밥 먹기"
          disabled
          className="mb-2 w-full rounded border px-2 py-1 text-sm"
        />
        <button className="mb-4 rounded bg-blue-600 px-4 py-2 text-sm text-white">
          내 풀이 보기
        </button>

        <div className="space-y-2">
          {submissions.map((sub, idx) => (
            <SubmissionCard
              key={idx}
              {...sub}
              isSelected={idx === selectedIdx}
              onClick={() => onSelect(idx)}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
