import ExplanationPanel from './components/ExplanationPanel';
import ProblemPanel from './components/ProblemPanel';

export default function ProblemRoomProblems() {
  return (
    <div className="flex min-h-screen">
      <ExplanationPanel />
      <ProblemPanel />
    </div>
  );
}
