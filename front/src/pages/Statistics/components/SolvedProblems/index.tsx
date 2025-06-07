const DUMMY: { isSolve: boolean; title: string; problemNum: number }[] = [
  {
    isSolve: true,
    title: '밥 먹기',
    problemNum: 1923,
  },
  {
    isSolve: false,
    title: '밥 먹기',
    problemNum: 1923,
  },
  {
    isSolve: true,
    title: '밥 먹기',
    problemNum: 1923,
  },
  {
    isSolve: true,
    title: '밥 먹기',
    problemNum: 1923,
  },
  {
    isSolve: false,
    title: '밥 먹기',
    problemNum: 1923,
  },
];

export default function SolvedProblems() {
  return (
    <section className="h-[36rem] w-full rounded-xl border border-gray-200 bg-white p-6 shadow-md">
      <h2 className="mb-4 text-lg font-semibold text-gray-800">
        오늘 풀이한 문제
      </h2>
      <ul className="space-y-2">
        {DUMMY.map((problem) => {
          return (
            <li className="flex cursor-pointer items-center gap-2 rounded-sm border border-gray-300 bg-gray-50 px-5 py-3 font-medium text-gray-800 shadow-sm hover:bg-gray-100">
              <span className="w-10 flex-shrink-0">
                {problem.isSolve && (
                  <img
                    src="/icons/solve-icon.svg"
                    className="aspect-square h-6 w-6"
                    alt="solve-icon"
                  />
                )}
              </span>
              <span className="font-medium">{problem.problemNum}.</span>
              {problem.title}
            </li>
          );
        })}
      </ul>
    </section>
  );
}
