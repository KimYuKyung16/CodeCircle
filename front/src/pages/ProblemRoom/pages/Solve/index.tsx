import CodeEditorPanel from './components/CodeEditorPanel';
import ProblemInformationPanel from './components/ProblemInformationPanel';

export default function ProblemRoomSolve() {
  return (
    <div className="flex min-h-screen">
      <ProblemInformationPanel />
      <CodeEditorPanel />
    </div>
  );
}
