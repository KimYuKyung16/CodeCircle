import Header from '@components/Header';
import RoomCard from './components/RoomCard';

const DUMMY = [
  {
    id: 1,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: true,
    isActive: false,
  },
  {
    id: 2,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: false,
    isActive: true,
  },
  {
    id: 3,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: false,
    isActive: false,
  },
  {
    id: 4,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: false,
    isActive: false,
  },
  {
    id: 5,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: false,
    isActive: false,
  },
  {
    id: 6,
    title: '문제풀이방1',
    langunage: 'Javascript',
    host: '김유경',
    participants: '4/8',
    isPrivate: false,
    isActive: true,
  },
];

export default function ProblemRoom() {
  return (
    <>
      <Header />
      <main className="min-h-default flex justify-center bg-gray-100 px-8 py-6">
        <div className="w-full max-w-6xl">
          <div className="mb-6 flex items-center justify-between px-2">
            <h1 className="text-md font-bold">문제풀이방</h1>
            <button className="cursor-pointer rounded-md bg-blue-600 px-4 py-2 text-sm text-white hover:bg-blue-700">
              방 생성하기
            </button>
          </div>

          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {DUMMY.map((room) => (
              <RoomCard key={room.id} {...room} />
            ))}
          </div>
        </div>
      </main>
    </>
  );
}
