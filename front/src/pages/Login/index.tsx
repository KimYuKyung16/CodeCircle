import Header from '@components/Header';
import LoginForm from './components/Form';
import SNSButton from './components/SNSButton';
import Footer from '@components/Footer';

export default function Login() {
  return (
    <div className="bg-primary-950 h-full min-h-screen w-full">
      <Header />
      <main className="bg-primary-950 flex h-full min-h-[calc(100vh-176px)] w-full items-center justify-center px-4">
        <section className="mt-10 w-full max-w-sm space-y-8 rounded-lg bg-white p-8 shadow-md">
          <h1 className="text-center text-2xl font-bold text-gray-800">
            CodeCircle
          </h1>
          <LoginForm />
          <div className="space-y-4 border-t-1 border-gray-300 pt-3">
            <div className="pt-2 text-center text-sm text-gray-500">
              간편하게 시작하기
            </div>
            <div className="flex justify-center space-x-4">
              <SNSButton type="google" />
              <SNSButton type="github" />
            </div>
          </div>
        </section>
      </main>
      <Footer />
    </div>
  );
}
