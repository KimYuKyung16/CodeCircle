const ROOM_DUMMY = {
  title: '코드푸는방',
  host: {
    avatarUrl: '/images/avatar.png',
    name: '김유경',
  },
  limitTime: 1000000,
  language: 'Javascript',
  problems: [
    {
      num: 1923,
      title: '밥 먹기',
    },
    {
      num: 1923,
      title: '밥 먹기',
    },
  ],
};

export default function RoomSetting() {
  return (
    <section className="max-w-xs flex-1 space-y-4 bg-gray-100 p-4">
      <div className="flex items-center space-x-4 rounded-lg bg-white p-4">
        <img
          src={ROOM_DUMMY.host.avatarUrl}
          alt="Profile"
          className="h-10 w-10 rounded-full object-cover"
        />
        <span className="font-medium">{ROOM_DUMMY.host.name}</span>
      </div>

      <div className="space-y-2 text-sm text-gray-600">
        <div className="flex justify-between">
          <span className="font-medium">제한시간</span>
          <span className="text-black">{ROOM_DUMMY.limitTime}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">풀이언어</span>
          <span className="text-black">{ROOM_DUMMY.language}</span>
        </div>
      </div>

      <div>
        <h5 className="mb-1 text-sm text-gray-600">풀이할 문제</h5>
        <ul className="min-h-[120px] rounded-lg bg-white p-4">
          {ROOM_DUMMY.problems.map((problem) => {
            return (
              <li className="border-b border-gray-300 py-2 text-sm text-gray-600">
                {problem.num}. {problem.title}
              </li>
            );
          })}
        </ul>
      </div>

      <button className="w-full cursor-pointer rounded-md bg-[#2756FE] py-2 font-semibold text-white">
        시작하기
      </button>
    </section>
  );
}
