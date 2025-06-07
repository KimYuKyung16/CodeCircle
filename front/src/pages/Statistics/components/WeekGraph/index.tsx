import {
  Chart as ChartJS,
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
);

const labels = ['6/1', '6/2', '6/3', '6/4', '6/5', '6/6'];
const datas = [2, 4, 3, 6, 5, 7];

export default function WeekGraph() {
  const data = {
    labels,
    datasets: [
      {
        label: '주간 문제 해결 수',
        data: datas,
        borderColor: 'rgb(59, 130, 246)',
        backgroundColor: 'rgba(59, 130, 246, 0.3)',
        tension: 0.3,
        fill: true,
        pointRadius: 4,
        pointHoverRadius: 6,
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        display: false,
      },
      title: {
        display: false,
      },
    },
    scales: {
      x: {
        ticks: {
          color: '#6B7280',
        },
      },
      y: {
        ticks: {
          color: '#6B7280',
        },
        beginAtZero: true,
      },
    },
  };
  return (
    <section className="flex flex-col gap-1">
      <h5 className="pl-2 text-xs font-semibold text-gray-600">
        주간 문제 해결 수
      </h5>
      <div className="rounded-lg bg-white p-4 shadow-md">
        <Line data={data} options={options} />
      </div>
    </section>
  );
}
