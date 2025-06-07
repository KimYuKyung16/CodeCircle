import { useState } from 'react';
import RoomForm from './components/RoomForm';
import ProblemSelect from './components/ProblemSelect';

export default function ProblemRoomCreate() {
  const [selectedProblems, setSelectedProblems] = useState<string[]>([]);

  const handleSelectProblem = (title: string) => {
    if (!selectedProblems.includes(title)) {
      setSelectedProblems([...selectedProblems, title]);
    }
  };
  return (
    <div className="flex min-h-screen bg-white">
      <RoomForm selectedProblems={selectedProblems} />
      <ProblemSelect onSelect={handleSelectProblem} />
    </div>
  );
}
