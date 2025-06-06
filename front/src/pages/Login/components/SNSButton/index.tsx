import clsx from 'clsx';

type SNSType = 'google' | 'github';

type SNSInfo = {
  src: string;
  style: string;
};

const SNS_INFO: Record<SNSType, SNSInfo> = {
  google: {
    src: '/icons/google-icon.svg',
    style: 'bg-white text-black',
  },
  github: {
    src: '/icons/github-icon.svg',
    style: 'bg-black text-white',
  },
};

export default function SNSButton({ type }: { type: SNSType }) {
  return (
    <button
      type="button"
      className={clsx(
        'flex w-full cursor-pointer items-center justify-center rounded-md border px-4 py-2',
        SNS_INFO[type].style
      )}
    >
      <img src={SNS_INFO[type].src} alt={type} className="mr-2 h-5 w-5" />
      {type}
    </button>
  );
}
