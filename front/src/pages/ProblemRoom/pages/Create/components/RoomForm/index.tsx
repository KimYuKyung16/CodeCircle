interface RoomFormProps {
  selectedProblems: string[];
}

export default function RoomForm({ selectedProblems }: RoomFormProps) {
  return (
    <div className="flex w-full max-w-md flex-1 flex-col space-y-6 border-r border-gray-200 bg-gray-100">
      <div className="bg-primary-950 flex items-center justify-between border-black py-4 text-lg font-semibold text-white">
        <button>{'< 나가기'}</button>
        <span>방 생성</span>
        <div className="w-10" />
      </div>
      <div className="flex flex-col gap-y-3 px-5">
        <div className="flex flex-col">
          <label className="mb-1 block text-xs font-semibold text-black">
            방 이름
          </label>
          <input className="w-full rounded-md border border-gray-300 bg-white p-2 text-black" />
          <p className="self-end text-xs text-gray-700">
            20자 이내로 적어주세요
          </p>
        </div>

        <div>
          <div className="mb-1 flex justify-between text-xs">
            <label className="font-semibold">풀이할 문제</label>
            <span>{selectedProblems.length}문제</span>
          </div>
          <div className="min-h-48 space-y-2 rounded-md border border-gray-300 bg-white p-2 text-black">
            {selectedProblems.map((title, idx) => (
              <div
                key={idx}
                className="border-b border-gray-300 px-2 py-1 text-sm"
              >
                {title}
              </div>
            ))}
          </div>
        </div>

        <div>
          <label className="mb-1 block text-xs font-semibold">제한 시간</label>
          <input
            className="w-full rounded-md border border-gray-300 bg-white p-2 text-black"
            placeholder="00시간 00분"
          />
        </div>

        <div>
          <label className="mb-1 block text-xs font-semibold">풀이 언어</label>
          <input className="w-full rounded-md border border-gray-300 bg-white p-2 text-black" />
        </div>

        <div>
          <label className="mb-1 block text-xs font-semibold">패스워드</label>
          <input className="w-full rounded-md border border-gray-300 bg-white p-2 text-black" />
        </div>

        <div className="w-full bg-amber-200">
          <button className="w-full cursor-pointer rounded-md bg-[#2756FE] py-3 text-white">
            생성하기
          </button>
        </div>
      </div>
    </div>
  );
}
