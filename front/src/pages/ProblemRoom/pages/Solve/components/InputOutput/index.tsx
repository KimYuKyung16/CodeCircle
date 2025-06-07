export default function InputOutput() {
  return (
    <section className="space-y-4">
      <div>
        <h3 className="font-semibold">입력</h3>
        <p>입력 설명이 들어갑니다.</p>
      </div>
      <div>
        <h3 className="font-semibold">출력</h3>
        <p>출력 설명이 들어갑니다.</p>
      </div>
      <div className="flex gap-4">
        <div className="w-1/2 rounded bg-gray-200 p-2">예제 입력1</div>
        <div className="w-1/2 rounded bg-gray-200 p-2">예제 출력1</div>
      </div>
    </section>
  );
}
