export default function SolveCountPanel() {
  return (
    <section className="flex gap-4">
      <div className="bg-primary-900 flex-1 rounded-lg p-7 text-center text-white shadow-md">
        <div className="text-md font-medium">해결한 문제</div>
        <div className="mt-2 text-2xl font-bold">30개</div>
      </div>
      <div className="flex-1 rounded-lg bg-gray-500 p-7 text-center text-white shadow-md">
        <div className="text-md font-medium">해결못한 문제</div>
        <div className="mt-2 text-2xl font-bold">2개</div>
      </div>
    </section>
  );
}
