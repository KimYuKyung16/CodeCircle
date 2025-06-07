const PARTICIPANTS_DUMMY = [
  {
    id: 1,
    name: '김유경',
    avatarUrl: '/images/avatar.png',
  },
  {
    id: 2,
    name: '김유경',
    avatarUrl: '/images/avatar.png',
  },
  {
    id: 3,
    name: '김유경',
    avatarUrl: '/images/avatar.png',
  },
];

export default function ParticipantList() {
  return (
    <section className="w-full flex-1 space-y-2 bg-white p-4">
      {PARTICIPANTS_DUMMY.map((participant) => (
        <div
          key={participant.id}
          className="flex items-center space-x-2 rounded-md bg-gray-100 p-3"
        >
          <img
            src={participant.avatarUrl}
            alt="Profile"
            className="h-8 w-8 rounded-full object-cover"
          />
          <span className="text-sm text-gray-800">{participant.name}</span>
        </div>
      ))}
    </section>
  );
}
