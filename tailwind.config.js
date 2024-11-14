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
            fontSize: {
                "sp-default": "0.8125rem",
            },
            fontFamily: {
                sans: ["M PLUS 1p", "sans-serif"],
            }
        },
        screens: {
            'sp': '390px',
            // => @media (min-width:390px)
        }
    },
    plugins: [
    ],
}

