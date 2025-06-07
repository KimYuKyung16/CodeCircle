const submissions = [
  {
    date: '2025-06-03',
    time: '23ms',
    memory: '23ms',
    status: 'success',
  },
  {
    date: '2025-06-03',
    time: '-ms',
    memory: '-ms',
    status: 'fail',
  },
];

export default function Submissions() {
  return (
    <section className="mt-2 w-full text-sm text-gray-700">
      <table className="w-full table-fixed border-separate border-spacing-y-2">
        <thead className="hidden">
          <tr>
            <th>날짜</th>
            <th>시간</th>
            <th>메모리</th>
          </tr>
        </thead>
        <tbody>
          {submissions.map((sub, idx) => (
            <tr key={idx} className="border-b border-gray-200 text-gray-800">
              <td className="py-1">
                <div className="flex items-center gap-2 font-medium">
                  {sub.status === 'success' && (
                    <img
                      src="/icons/solve-icon.svg"
                      className="aspect-square h-5 w-5"
                    />
                  )}
                  <span>{sub.date}</span>
                </div>
              </td>
              <td className="py-1 text-gray-600">
                실행 시간 <span className="font-semibold">{sub.time}</span>
              </td>
              <td className="py-1 text-gray-600">
                메모리 <span className="font-semibold">{sub.memory}</span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}
