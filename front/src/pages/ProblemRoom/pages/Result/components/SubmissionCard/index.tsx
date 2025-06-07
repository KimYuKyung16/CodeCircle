interface SubmissionCardProps {
  user: string;
  status: '정답' | '오답' | '풀이중';
  time: string;
  execTime: string;
  memory: string;
  isSelected?: boolean;
  onClick?: () => void;
}

export default function SubmissionCard({
  user,
  status,
  time,
  execTime,
  memory,
  isSelected = false,
  onClick,
}: SubmissionCardProps) {
  const statusColor = {
    정답: 'bg-green-500',
    오답: 'bg-gray-500',
    풀이중: 'bg-gray-400 opacity-50',
  };

  const borderColor = isSelected ? 'border-2 border-blue-500' : 'border';

  return (
    <div
      className={`flex cursor-pointer items-center justify-between rounded-lg px-4 py-3 ${borderColor} bg-white`}
      onClick={onClick}
    >
      <div className="space-y-1 text-sm">
        <div className="flex items-center gap-2">
          <span
            className={`rounded px-2 py-1 text-xs text-white ${statusColor[status]}`}
          >
            {status}
          </span>
          <span className="font-semibold">{user}</span>
        </div>
        <div className="text-gray-600">풀이 시간 {time}</div>
      </div>
      {status !== '풀이중' && (
        <div className="text-right text-sm text-gray-700">
          <div>
            실행 시간
            <span className={isSelected ? 'text-blue-600' : ''}>
              {execTime}
            </span>
          </div>
          <div>
            메모리
            <span className={isSelected ? 'text-blue-600' : ''}>{memory}</span>
          </div>
        </div>
      )}
    </div>
  );
}
