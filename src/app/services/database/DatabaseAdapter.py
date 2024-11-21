from abc import ABC, abstractmethod
from typing import Any

class DatabaseAdapter(ABC):
    """Abstract class for database adapters"""
    def __init__(self, name : str):
        self.name : str = name

    @abstractmethod
    def connect(self) -> bool:
        """Connect to database"""
        pass

    @abstractmethod
    def disconnect(self):
        """Disconnect from database"""
        pass

    @abstractmethod
    def query(self, query : str) -> Any:
        """Query database"""
        pass

    @abstractmethod
    def getLocationID(self, locationCode: str) -> int | None:
        """Get location ID"""
        pass

    @abstractmethod
    def analytics_query(self, query : str) -> Any:
        """Query database"""
        pass