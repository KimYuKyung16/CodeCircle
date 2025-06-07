interface CodeViewerPanelProps {
  code: string;
}

export default function CodeViewerPanel({ code }: CodeViewerPanelProps) {
  return (
    <div className="flex flex-1 flex-col bg-gray-900 text-white">
      <div className="flex justify-end p-4">
        <button className="rounded bg-gray-700 px-4 py-1">JavaScript</button>
      </div>

      <div className="flex-1 overflow-auto px-6 pb-6">
        <pre className="rounded border border-gray-800 bg-gray-900 p-4 font-mono text-sm whitespace-pre-wrap">
          {code}
        </pre>
      </div>
    </div>
  );
}
