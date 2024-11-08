/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        "default" : "#1E1E1E",
        "label" : "#555",
        "frame" : "#D9D9D9",
        "sub-button" : "#5F8565",
        "interactive" : "#8DC48F",
        "link" : "#8DA3C4",
        "link-hover" : "#6F8ECE",
        "danger" : "#F45555",
      },
      fontFamily: {
        sans: ["M PLUS 1p", "sans-serif"],
      }
    },
  },
  plugins: [],
}

