/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["/src/**/*.{html,js}"],
  mode: 'jit',
  purge: [
    './dist/**/*.html',
    './src/**/*.{js,jsx,ts,tsx,vue,css,html}',
  ],
  theme: {
    extend: {
      colors: {
        'current': '#262626',
        'not-current': '#E2E2E2',
        'holiday': '#DC143C',
      },
      height: {
        '128': '42rem',
        'ss': '0.2rem',
      }
    },
  },
  plugins: [],
}

