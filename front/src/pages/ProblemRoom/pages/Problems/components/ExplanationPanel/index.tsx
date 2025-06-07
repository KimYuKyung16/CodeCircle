import Timer from '@pages/ProblemRoom/pages/components/Timer';
import ProblemHeader from '../../../components/ProblemHeader';

export default function ExplanationPanel() {
  return (
    <div className="flex h-screen w-full max-w-lg flex-1 flex-col justify-between text-white">
      <ProblemHeader />
      <div className="h-full p-6 text-black">
        <p className="mb-2 text-sm">
          제일 마지막에 제출한 내용으로 풀이를 진행할 수 있습니다.
        </p>
        <p className="text-sm">
          모든 문제를 푼 후에는 풀이 완료 버튼을 클릭해주세요.
        </p>
      </div>
      <Timer />
    </div>
  );
}
