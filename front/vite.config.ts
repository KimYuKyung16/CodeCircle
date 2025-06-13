import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tailwindcss from '@tailwindcss/vite';
import path from 'path';

export default defineConfig({
  server: {
    host: true,
    watch: {
      usePolling: true,
    },
  },
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      '@apis': path.resolve(__dirname, 'src/apis'),
      '@components': path.resolve(__dirname, 'src/components'),
      '@hooks': path.resolve(__dirname, 'src/hooks'),
      '@interfaces': path.resolve(__dirname, 'src/interfaces'),
      '@lib': path.resolve(__dirname, 'src/lib'),
      '@pages': path.resolve(__dirname, 'src/pages'),
      '@utils': path.resolve(__dirname, 'src/utils'),
      '@states': path.resolve(__dirname, 'src/states'),
      '@contexts': path.resolve(__dirname, 'src/contexts'),
      '@styles': path.resolve(__dirname, 'src/styles'),
      '@errors': path.resolve(__dirname, 'src/errors'),
      '@routes': path.resolve(__dirname, 'src/routes'),
    },
  },
});
