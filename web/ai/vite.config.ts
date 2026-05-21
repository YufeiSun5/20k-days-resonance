import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  base: '/ai/',
  plugins: [react()],
  server: {
    proxy: {
      '/api': 'http://49.232.169.142'
    }
  },
  build: {
    outDir: '../../server/deploy/static/ai',
    emptyOutDir: true
  }
})
