// Menu para dispositivos móveis
const menuBtn = document.getElementById("menuBtn");
const menu = document.getElementById("menu");

menuBtn.addEventListener("click", () => {
  menu.classList.toggle("open");
});

menu.querySelectorAll("a").forEach((link) => {
  link.addEventListener("click", () => menu.classList.remove("open"));
});

// Dia demonstrativo no desenho do celular
const dayDemo = document.getElementById("dayDemo");
let demoDay = 14;

setInterval(() => {
  demoDay = demoDay >= 28 ? 1 : demoDay + 1;
  dayDemo.textContent = demoDay;
}, 2000);

// Calculadora do ciclo
const form = document.getElementById("calcForm");
const errorEl = document.getElementById("error");
const results = document.getElementById("results");
const lastPeriodInput = document.getElementById("lastPeriod");

// Evita selecionar uma data futura. Usa a data local do computador.
const todayForInput = new Date();
const localToday = new Date(
  todayForInput.getTime() - todayForInput.getTimezoneOffset() * 60000
).toISOString().split("T")[0];
lastPeriodInput.max = localToday;

const fmt = (date) =>
  date.toLocaleDateString("pt-BR", {
    day: "2-digit",
    month: "short",
    year: "numeric"
  });

const addDays = (date, numberOfDays) => {
  const result = new Date(date);
  result.setDate(result.getDate() + numberOfDays);
  return result;
};

form.addEventListener("submit", (event) => {
  event.preventDefault();
  errorEl.textContent = "";

  const last = lastPeriodInput.value;
  const cycle = parseInt(document.getElementById("cycleLength").value, 10);
  const period = parseInt(document.getElementById("periodLength").value, 10);

  if (!last) {
    errorEl.textContent = "Informe a data da última menstruação.";
    results.classList.remove("show");
    return;
  }

  const start = new Date(last + "T00:00:00");
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  if (start > today) {
    errorEl.textContent = "A data não pode ser futura.";
    results.classList.remove("show");
    return;
  }

  if (Number.isNaN(cycle) || cycle < 21 || cycle > 45) {
    errorEl.textContent = "A duração do ciclo deve estar entre 21 e 45 dias.";
    results.classList.remove("show");
    return;
  }

  if (Number.isNaN(period) || period < 1 || period > 10) {
    errorEl.textContent = "A menstruação deve durar entre 1 e 10 dias.";
    results.classList.remove("show");
    return;
  }

  const nextPeriod = addDays(start, cycle);
  const ovulation = addDays(start, cycle - 14);
  const fertileStart = addDays(ovulation, -5);
  const fertileEnd = addDays(ovulation, 1);

  const diffDays = Math.floor((today - start) / 86400000);
  const currentDay = (diffDays % cycle) + 1;

  document.getElementById("nextPeriod").textContent = fmt(nextPeriod);
  document.getElementById("ovulation").textContent = fmt(ovulation);
  document.getElementById("fertile").textContent =
    `${fmt(fertileStart)} a ${fmt(fertileEnd)}`;
  document.getElementById("currentDay").textContent = `Dia ${currentDay}`;

  results.classList.add("show");
});

// Cadastro demonstrativo: valida os campos, mas não salva nem envia dados.
const signupForm = document.getElementById("signupForm");
const signupError = document.getElementById("signupError");
const signupSuccess = document.getElementById("signupSuccess");

signupForm.addEventListener("submit", (event) => {
  event.preventDefault();
  signupError.textContent = "";
  signupSuccess.hidden = true;

  const name = document.getElementById("signupName").value.trim();
  const email = document.getElementById("signupEmail").value.trim();
  const pass = document.getElementById("signupPass").value;
  const pass2 = document.getElementById("signupPass2").value;
  const terms = document.getElementById("signupTerms").checked;

  if (name.length < 2) {
    signupError.textContent = "Informe seu nome.";
    return;
  }

  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    signupError.textContent = "Informe um e-mail válido.";
    return;
  }

  if (pass.length < 8) {
    signupError.textContent = "A senha deve ter pelo menos 8 caracteres.";
    return;
  }

  if (pass !== pass2) {
    signupError.textContent = "As senhas não coincidem.";
    return;
  }

  if (!terms) {
    signupError.textContent = "Aceite os termos para continuar.";
    return;
  }

  signupSuccess.hidden = false;
  signupForm.reset();
});
