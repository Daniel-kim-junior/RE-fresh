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
        'current': '#000000',
        'not-current': '#B2B2B2',
        'holiday': '#DC143C',
        'cal-modal': '#FFFAFA',
        'table-rgba': 'rgba(180, 180, 180)',
      },
      height: {
        '128': '50rem',
        'ss': '0.2rem',
      },
      width: {
        '128': '80rem'
      },
      spacing: {
        'cal-modal': '50%'
      }
    },
  },
  plugins: [],
}

