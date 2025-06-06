import FormInput from '../FormInput';

export default function LoginForm() {
  return (
    <form className="space-y-4">
      <div className="space-y-2">
        <FormInput type="email" placeholder="이메일" />
        <FormInput type="password" placeholder="비밀번호" />
      </div>
      <div className="space-y-2">
        <button
          type="submit"
          className="w-full cursor-pointer rounded-md bg-blue-600 px-4 py-2 font-semibold text-white hover:bg-blue-700 focus:ring-2"
        >
          로그인
        </button>
        <button
          type="button"
          className="w-full cursor-pointer rounded-md bg-gray-100 px-4 py-2 font-semibold text-gray-800 hover:bg-gray-200"
        >
          회원가입
        </button>
      </div>
    </form>
  );
}
