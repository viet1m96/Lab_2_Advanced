const submitStatus = document.getElementById('submitStatus');
const form = document.getElementById('input-form');
function addAdvice(msg) {
    const d = document.createElement('div');
    d.className = 'advice';
    d.textContent = msg;
    d.style.color = 'red';
    submitStatus.appendChild(d);
}
function clearAdvices() {
    document.querySelectorAll('.advice').forEach(el => el.remove());
}

form.addEventListener('submit', async(e) => {
    e.preventDefault();
    clearAdvices();
    if (!checkX || !checkR) {
        if (!checkX) addAdvice('Please pick correct X!');
        if (!checkR) addAdvice('Please pick R!');
        return;
    }

    const params = new URLSearchParams(new FormData(form));
    const url = form.action + '?' + params.toString();
    try {
        const response = await fetch(url, {
            method: form.method,
            credentials: "same-origin",
            headers: {'Accept' : 'application/json'}
        });
        if(!response.ok) throw new Error("Request failed");

        const result = await response.json();
        await showResultOverlay(Boolean(result.hit));
        appendNewRow(result);
    } catch (err) {
        alert("Error while submitting: " + err.message);
    }


});
function showResultOverlay(hit) {
    return new Promise((resolve) => {
        const box = document.getElementById('result-overlay');
        const text = document.getElementById('result-overlay-text');
        if (!box || !text) {
            resolve();
            return;
        }

        text.textContent = hit ? 'HIT' : 'NO HIT';
        box.classList.remove('hit', 'miss', 'show');
        box.classList.add(hit ? 'hit' : 'miss');

        void box.offsetWidth;
        box.classList.add('show');

        box.addEventListener('animationend', () => {
            box.classList.remove('show');
            resolve();
        }, { once: true });
    });
}

function appendNewRow(result) {
    const table = document.getElementById("final-results").querySelector("tbody");
    const row = document.createElement("tr");
    row.innerHTML = `
            <td>${result.hit}</td>
            <td>${result.x}</td>
            <td>${result.y}</td>
            <td>${result.r}</td>
            <td>${result.calTime}</td>
            <td>${result.releaseTime}</td>
        `;
    table.appendChild(row);
    drawGraph();
}
async function fetchOnReload() {
    try {
        const res = await fetch('./history', {
            method: 'GET',
            credentials: 'same-origin',
            headers: { 'Accept': 'application/json' }
        });
        if (!res.ok) return;

        const data = await res.json();
        const list = Array.isArray(data) ? data : (data.history ?? []);
        if (!list || list.length === 0) return;
        for (const r of list) appendNewRow(r);
        realR = (Number)(list[list.length - 1].r);
    } catch (err) {
        alert("Load history failed " + err.message);
    }
}

window.addEventListener("DOMContentLoaded", () => {
    fetchOnReload().then( () => {
            const el = document.querySelector('input[name="R"][type="checkbox"][value="' + realR + '"]');
            if (el && !el.checked) {
                el.checked = true;
                el.dispatchEvent(new Event('change', { bubbles: true }));
            }
            drawGraph();
    }
    );

});




