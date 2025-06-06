export default function Header() {
  return (
    <header className="bg-primary-950 flex flex-row justify-between px-7 py-8">
      <div className="flex gap-24">
        <span className="text-white">CodeCircle</span>
        <nav className="flex flex-row gap-14 text-white">
          <a>내 문제풀이</a>
          <a>문제풀이방</a>
          <a>통계</a>
        </nav>
      </div>
      <div>
        <button className="text-white">로그인</button>
      </div>
    </header>
  );
}
