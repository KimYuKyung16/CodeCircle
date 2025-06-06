export default function FormInput({
  type,
  placeholder,
}: {
  type: string;
  placeholder: string;
}) {
  return (
    <input
      type={type}
      id={type}
      name={type}
      required
      placeholder={placeholder}
      className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:ring-blue-500"
    />
  );
}
