You can find the old design document on [DESIGN.md](./DESIG.md)

Welcome to the DV-Hub application demo, this project consist currently of the two following components:

<br />
<div align="center">
  <a href="#">
    <img src="https://github.com/IQSS/dataverse-frontend/assets/7512607/6c4d79e4-7be5-4102-88bd-dfa167dc79d3" alt="Logo" width="500">
  </a>
  <h2 align="center">DV-Hub</h2>
  <p align="center">
    <em>A solution for centralized metrics for the Dataverse community!</em>
    <br />
    <a href=""><strong>Explore the Docs »</strong></a>
    <br />
    <br />
    <a href=""</a> |
    <a href=""></a> |
  </p>
<br>
<h3>Progress Demo Videos</h3>
<p align="center">
  <a href=""></a><small><!--Date--></small> |
</p>
<h3>Chat with us!</h3>
</div>

---

### ⚠️ Important Information About the Dataverse Frontend ⚠️

> DV-Hub is currently in beta and under active development.

---

## Table of Contents

  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li><a href="#instructions"></a>Instructions</li>
    <li><a href="#"></a></li>
  </ol>

---

## About the Project

## Instructions

### Backend

1) Install makefile (Windows only)

```
    scoop install make
```

Starting the backend: 

```
    make run
```


### Frontend

Depending on your environment you could use brew *(MAC)* or scoop *(WIN)*, we are using poetry for dependency management so your path could be different, we use brew or scoop to install pipx -> poetry -> shiny. At the end the onlything you need to do to launch the project is open a poetry shell and `shiny run`. 

```
    pipx install poetry
    poetry install
    poetry shell
    shiny run
```
