from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import APIView, api_view
from rest_framework.response import Response
from rest_framework import generics
from django_filters import rest_framework as filters
from django.http import Http404
from .filters import LimitFilterBackend
from .permissions import IsGameOwnerOrReadOnly
from .permissions import IsSuperuserOrReadOnly
from django.contrib.auth.models import User
from rest_framework import permissions


from .serializers import (
    FeatureSerializer, PlatformSerializer, LanguageSerializer, 
    GenreSerializer, CompanySerializer, GameListSerializer, 
    GameDetailsSerializer, GameCreateSerializer, GameUpdateSerializer,
    UserSerializer
)
from games.models import (
    Feature, Platform, Language, Genre, Company, Game
)
from games.api.filters import GameFilter



# --------------------------------------------------------------------
# Feature API views
# --------------------------------------------------------------------
class FeatureList(generics.ListCreateAPIView):
    queryset = Feature.objects.all()
    serializer_class = FeatureSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )


class FeatureDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Feature.objects.all()
    serializer_class = FeatureSerializer    
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, IsSuperuserOrReadOnly,)


# --------------------------------------------------------------------
# Platform API views
# --------------------------------------------------------------------
class PlatformList(generics.ListCreateAPIView):
    queryset = Platform.objects.all()
    serializer_class = PlatformSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )


class PlatformDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Platform.objects.all()
    serializer_class = PlatformSerializer  
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )

    
# --------------------------------------------------------------------
# Language API views
# --------------------------------------------------------------------
class LanguageList(generics.ListCreateAPIView):
    queryset = Language.objects.all()
    serializer_class = LanguageSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )


class LanguageDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Language.objects.all()
    serializer_class = LanguageSerializer 
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, IsSuperuserOrReadOnly,)


# --------------------------------------------------------------------
# Genre API views
# --------------------------------------------------------------------
class GenreList(generics.ListCreateAPIView):
    queryset = Genre.objects.all()
    serializer_class = GenreSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )


class GenreDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Genre.objects.all()
    serializer_class = GenreSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, IsSuperuserOrReadOnly,)


# --------------------------------------------------------------------
# Company API views
# --------------------------------------------------------------------
class CompanyList(generics.ListCreateAPIView):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )


class CompanyDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, IsSuperuserOrReadOnly,)


# --------------------------------------------------------------------
# Game API views
# --------------------------------------------------------------------
class GameList(generics.ListCreateAPIView):
    queryset = Game.objects.all()
    #serializer_class = GameListSerializer
    filterset_class = GameFilter
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)


    def get_serializer_class(self):
        if self.request.method == "GET":
            return GameListSerializer
        else:
            return GameCreateSerializer

    def perform_create(self, serializer):
        serializer.save(user=self.request.user)


class GameDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Game.objects.all()
    #serializer_class = GameDetailsSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, IsGameOwnerOrReadOnly)

    def get_serializer_class(self):
        if self.request.method == "PUT" or self.request.method == "UPDATE":
            return GameUpdateSerializer
        else:
            return GameDetailsSerializer


# --------------------------------------------------------------------
# User API views
# --------------------------------------------------------------------
class UserList(generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    

class UserDetail(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer    
    