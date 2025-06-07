import { useState } from 'react';
import SubmissionListPanel from './components/SubmissionListPanel';
import CodeViewerPanel from './components/CodeViewerPanel';

const submissions = [
  {
    user: '김유경',
    status: '정답',
    time: '00:24:55',
    execTime: '23ms',
    memory: '23ms',
    code: 'import sys;\nimport sys;\nclass main() {',
  },
  {
    user: '김유경',
    status: '정답',
    time: '00:24:55',
    execTime: '23ms',
    memory: '23ms',
    code: '...',
  },
  {
    user: '김유경',
    status: '정답',
    time: '00:24:55',
    execTime: '23ms',
    memory: '23ms',
    code: '...',
  },
  {
    user: '김유경',
    status: '오답',
    time: '00:24:55',
    execTime: '23ms',
    memory: '23ms',
    code: '...',
  },
  {
    user: '김유경',
    status: '풀이중',
    time: '-',
    execTime: '-',
    memory: '-',
    code: '...',
  },
];

export default function ProblemRoomResult() {
  const [selectedIdx, setSelectedIdx] = useState(0);

  return (
    <div className="flex min-h-screen">
      <SubmissionListPanel
        submissions={submissions}
        selectedIdx={selectedIdx}
        onSelect={setSelectedIdx}
      />
      <CodeViewerPanel code={submissions[selectedIdx].code} />
    </div>
  );
}
