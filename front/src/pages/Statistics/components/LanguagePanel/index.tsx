import clsx from 'clsx';

type LanguageType = 'javascript' | 'java' | 'python';

const LANGUAGE_STYLE: Record<LanguageType, string> = {
  javascript: 'bg-yellow-300',
  java: 'bg-gray-500',
  python: 'bg-blue-400',
};

const DUMMY: {
  totalCount: number;
  languages: { language: LanguageType; count: number }[];
} = {
  totalCount: 18,
  languages: [
    {
      language: 'javascript',
      count: 5,
    },
    {
      language: 'java',
      count: 3,
    },
    {
      language: 'python',
      count: 10,
    },
  ],
};

export default function LanguagePanel() {
  return (
    <section className="flex flex-col gap-1">
      <h5 className="pl-2 text-xs font-semibold text-gray-600">언어 통계</h5>
      <div className="space-y-2 rounded-lg bg-white p-4 shadow-md">
        {DUMMY.languages.map((data) => {
          const percentage = (data.count / DUMMY.totalCount) * 100;
          console.log(percentage);
          return (
            <div>
              <div className="mb-1 flex items-center justify-between">
                <span className="text-sm font-semibold text-gray-900">
                  {data.language.charAt(0).toUpperCase() +
                    data.language.slice(1).toLowerCase()}
                </span>
                <span className="text-xs text-gray-600">{data.count}문제</span>
              </div>
              <div
                className={clsx('h-3 rounded', LANGUAGE_STYLE[data.language])}
                style={{ width: `${percentage}%` }}
              />
            </div>
          );
        })}
      </div>
    </section>
  );
}
