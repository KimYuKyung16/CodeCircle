interface ProblemSelectProps {
  onSelect: (title: string) => void;
}

export default function ProblemSelect({ onSelect }: ProblemSelectProps) {
  const dummyProblems = ['1923. 밥 먹기'];

  return (
    <div className="w-full flex-1 bg-white px-8 py-4">
      <h2 className="mb-4 text-lg font-bold">문제 선택</h2>
      <input
        type="text"
        className="mb-6 w-full max-w-md rounded-md border p-2"
        placeholder="문제 번호로 검색"
      />
      <div className="space-y-2">
        {dummyProblems.map((title, idx) => (
          <>
            <span className="text-xs">문제를 추가하려면 선택해주세요</span>
            <div
              key={idx}
              className="cursor-pointer rounded-md border border-gray-300 px-4 py-2 shadow-sm hover:bg-gray-100"
              onClick={() => onSelect(title)}
            >
              {title}
            </div>
          </>
        ))}
      </div>
    </div>
  );
}
