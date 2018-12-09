## Functionality

Creates database `postgres` and user `username` with password `password`.

## Usage

Execute a couple of commands inside the `docker` directory

Build container:
```bash
sudo docker build -t pip_lab4 .
```

Run docker:
```bash
sudo docker run -it --rm -p 5432:5432 pip_lab4
```
