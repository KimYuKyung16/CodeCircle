const PROBLEM_DUMMY = [
  {
    id: 1,
    num: 1923,
    title: '밥 먹기',
  },
  {
    id: 2,
    num: 1923,
    title: '밥 먹기',
  },
  {
    id: 3,
    num: 1923,
    title: '밥 먹기',
  },
];

export default function ProblemPanel() {
  return (
    <div className="flex flex-1 flex-col justify-between border-l border-gray-400">
      <div className="p-8">
        <h2 className="mb-4 text-xl font-bold">문제</h2>
        <div className="space-y-2">
          {PROBLEM_DUMMY.map((problem) => (
            <div
              key={problem.id}
              className="cursor-pointer rounded border border-gray-300 bg-gray-100 px-4 py-4 hover:bg-gray-200"
            >
              {problem.num}. {problem.title}
            </div>
          ))}
        </div>
      </div>

      <div className="flex justify-end p-4">
        <button className="w-full max-w-sm cursor-pointer rounded-md bg-blue-600 py-3 text-lg font-semibold text-white hover:bg-blue-700">
          풀이 완료
        </button>
      </div>
    </div>
  );
}
