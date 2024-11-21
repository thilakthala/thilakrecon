# src/main.py
from fastapi import FastAPI
from src.app.core.reports.reconHrlyStatus import router as reconHrlyStatusRouter
from src.app.core.reports.reconStatus import router as reconStatusRouter
from src.app.core.reports.reconFailureDetail import router as reconFailureRouter
# Create the FastAPI app instance
app = FastAPI()

@app.get("/")
def read_root():
    return {"message": "Welcome to Testing of Python & Fast Api "}

# Include routers
app.include_router(reconHrlyStatusRouter, prefix="/api", tags=["reconHrlyStatus"])
app.include_router(reconStatusRouter, prefix="/api", tags=["reconStatus"])
app.include_router(reconFailureRouter, prefix="/api", tags=["reconFailureStatus"])