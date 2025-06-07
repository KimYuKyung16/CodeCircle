import Header from '@components/Header';
import SolvedProblems from './components/SolvedProblems';
import SolveCountPanel from './components/SolveCountPanel';
import LanguagePanel from './components/LanguagePanel';
import WeekGraph from './components/WeekGraph';

export default function Statistics() {
  return (
    <>
      <Header />
      <main className="min-h-default bg-gray-100 pt-8">
        <div className="mx-auto flex h-full max-w-6xl items-start gap-6 px-5">
          <div className="flex w-full max-w-md flex-col gap-y-5">
            <SolveCountPanel />
            <LanguagePanel />
            <WeekGraph />
          </div>

          <SolvedProblems />
        </div>
      </main>
    </>
  );
}
