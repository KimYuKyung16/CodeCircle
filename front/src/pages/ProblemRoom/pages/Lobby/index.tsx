import ParticipantList from './components/ParticipantList';
import RoomSetting from './components/RoomSetting';

export default function ProblemRoomLobby() {
  return (
    <div className="h-screen w-full bg-gray-100">
      <header className="bg-primary-950 flex items-center justify-between px-4 py-4 text-white">
        <button>{'< 나가기'}</button>
        <h1 className="font-semibold">코드푸는방</h1>
        <div className="w-10" />
      </header>
      <main className="m-auto flex h-[calc(100vh-56px)] w-full max-w-4xl bg-red-50">
        <RoomSetting />
        <ParticipantList />
      </main>
    </div>
  );
}
