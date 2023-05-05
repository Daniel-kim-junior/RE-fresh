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
        'today-color': '#B3E0FF',
        'table-rgba': 'rgba(160, 160, 160)',
      },
      height: {
        'table-height': '500px',
        'ss': '0.2rem',
        'td-height': '90px',
      },
      width: {
        '128': '80rem',
        'table-width': '840px',
        'td-width': '120px'
      },
      spacing: {
        'cal-modal': '60%',
        'cal-header': '500px',
        'table-header-top': '76px',
      }
    },
  },
  plugins: [],
}

