import { useState } from 'react';
import clsx from 'clsx';
import Timer from '@pages/ProblemRoom/pages/components/Timer';
import ProblemHeader from '@pages/ProblemRoom/pages/components/ProblemHeader';
import Submissions from '../Submissions';
import InputOutput from '../InputOutput';
import Description from '../Description';

const tabs = ['문제 설명', '입출력', '제출내역'] as const;

type TabType = (typeof tabs)[number];

export default function ProblemInformationPanel() {
  const [activeTab, setActiveTab] = useState<TabType>('문제 설명');

  return (
    <div className="flex w-[40%] flex-col border-r border-gray-300 bg-white text-sm">
      <ProblemHeader />

      <div className="flex flex-1 flex-col gap-4 overflow-y-auto p-4">
        <h2 className="text-xl font-bold">1923. 밥먹기</h2>
        <div className="flex gap-4 text-sm font-semibold">
          {tabs.map((tab) => (
            <button
              key={tab}
              onClick={() => setActiveTab(tab)}
              className={clsx(
                activeTab === tab ? 'text-blue-600' : 'text-gray-500'
              )}
            >
              {tab}
            </button>
          ))}
        </div>

        {activeTab === '문제 설명' && <Description />}
        {activeTab === '입출력' && <InputOutput />}
        {activeTab === '제출내역' && <Submissions />}
      </div>

      <Timer />
    </div>
  );
}
