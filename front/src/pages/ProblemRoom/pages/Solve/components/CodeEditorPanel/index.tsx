import { useState, useRef, useEffect } from 'react';

export default function CodeEditorPanel() {
  const [code, setCode] = useState('');
  const textareaRef = useRef<HTMLTextAreaElement>(null);
  const [lineCount, setLineCount] = useState(1);

  useEffect(() => {
    const lines = code.split('\n').length;
    setLineCount(lines);
  }, [code]);

  return (
    <div className="flex flex-1 flex-col bg-gray-900 text-white">
      <div className="flex justify-end p-4">
        <button className="rounded bg-gray-700 px-4 py-1">JavaScript</button>
      </div>

      <div className="flex flex-1 overflow-auto px-4 pb-2">
        <div className="pr-2 text-right font-mono text-sm text-gray-400 select-none">
          {Array.from({ length: lineCount }, (_, i) => (
            <div key={i}>{i + 1}</div>
          ))}
        </div>

        <textarea
          ref={textareaRef}
          className="min-h-[300px] w-full resize-none rounded-md border border-gray-800 bg-gray-900 p-2 font-mono text-sm text-white outline-none"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          spellCheck={false}
        />
      </div>

      <div className="border-t border-gray-800 px-4 py-2 text-sm">
        <h2 className="mb-1 font-semibold">실행 결과</h2>
        <div className="min-h-[60px] rounded bg-gray-800 p-2 whitespace-pre-wrap"></div>
      </div>

      <div className="flex justify-end gap-2 p-4">
        <button className="rounded bg-gray-600 px-4 py-2">테스트케이스</button>
        <button className="rounded bg-gray-600 px-4 py-2">실행</button>
        <button className="rounded bg-blue-600 px-4 py-2 text-white">
          제출
        </button>
      </div>
    </div>
  );
}
