interface RoomCardProps {
  title: string;
  langunage: string;
  host: string;
  participants: string;
  isPrivate?: boolean;
  isActive?: boolean;
}

export default function RoomCard({
  title,
  langunage,
  host,
  participants,
  isPrivate = false,
  isActive = false,
}: RoomCardProps) {
  return (
    <section
      className={`relative flex aspect-video w-full cursor-pointer flex-col justify-between rounded-lg border border-gray-300 bg-white p-4 shadow-md transition-all hover:scale-105`}
    >
      <div>
        <h3 className="text-lg font-semibold">{title}</h3>
        <p className="text-sm">{langunage}</p>
      </div>
      <div className="flex items-center justify-between text-sm">
        <span>{host}</span>
        <span className="flex items-center gap-1">
          {isPrivate && (
            <img src="/icons/lock-icon.svg" className="aspect-auto h-5 w-5" />
          )}
          {participants}
        </span>
      </div>
      {isActive && (
        <div className="bg-opacity-60 absolute inset-0 flex flex-col items-center justify-center rounded-lg bg-black/40 text-lg font-bold text-white">
          <img src="/icons/code-icon.svg" className="aspect-square h-15 w-15" />
          풀이 진행중
        </div>
      )}
    </section>
  );
}
