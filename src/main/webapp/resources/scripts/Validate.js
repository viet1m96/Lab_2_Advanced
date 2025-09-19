let checkX = false;
let checkY = false;
let checkR = false;


const inputX = document.getElementById('coordinateX');
const xFeedback = document.getElementById('xFeedback');
const inputR = document.getElementById('rBlock');
const yList = [-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2];
const ySelect = document.getElementById('ySelect');

const xStart = -5;
const xEnd = 5;

function validateNumberInRange(val, start, end) {
    const tmp = val.trim();
    const floatRe = /^[+-]?\d+(\.\d+)?$/;
    if (!floatRe.test(tmp)) return false;

    const num = Number(tmp);
    if (isNaN(num)) return false;
    if (num < start || num > end) return false;

    if (num === end) {
        const parts = tmp.split(".");
        if (parts.length === 2 && !/^0*$/.test(parts[1])) return false;
    }

    if (num === start) {
        const parts = tmp.replace("-", "").split(".");
        if (parts.length === 2 && !/^0*$/.test(parts[1])) return false;
    }
    return true;
}


function validateX() {
    inputX.addEventListener('input', () => {
            const inpFromUser = inputX.value.trim();
            if(inpFromUser === '') {
                checkX = false;
                xFeedback.textContent = '';
                xFeedback.classList.remove('valid', 'invalid');
            } else if(validateNumberInRange(inpFromUser, xStart, xEnd)) {
                checkX = true;
                xFeedback.textContent = 'Valid value ✔';
                xFeedback.classList.add('valid');
                xFeedback.classList.remove('invalid');
            } else {
                checkX = false;
                xFeedback.innerHTML = `Invalid value ✖`;
                xFeedback.classList.add('invalid');
                xFeedback.classList.remove('valid');
            }
        }
    );
}

validateX('coordinateX', 'xFeedback', -3, 3);

inputR.addEventListener("change", (e) => {
    if(e.target.matches('input[type="checkbox"][name="R"]') && e.target.checked) {
        inputR.querySelectorAll('input[name="R"]').forEach(box => {
            if(box !== e.target) {
                box.checked = false;
            } else {
                checkR = true;
                realR = box.value;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                drawGraph();
            }
        })
    } else if (e.target.matches('input[type="checkbox"][name="R"]') && !e.target.checked) {
        realR = null;
        checkR = false;
    }
});

inputX.addEventListener("input", (e) => {
    let value = e.target.value;
    e.target.value = value.replace(/[^0-9,.-]/g, "");
})




